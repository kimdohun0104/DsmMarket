package com.dsm.app.viewModel

import com.dsm.app.BaseTest
import com.dsm.app.createHttpException
import com.dsm.domain.entity.PurchaseDetail
import com.dsm.domain.entity.Recommend
import com.dsm.domain.usecase.*
import com.dsm.dsmmarketandroid.R
import com.dsm.dsmmarketandroid.presentation.mapper.PurchaseDetailModelMapper
import com.dsm.dsmmarketandroid.presentation.mapper.RecommendModelMapper
import com.dsm.dsmmarketandroid.presentation.ui.main.purchase.purchaseDetail.PurchaseDetailViewModel
import com.dsm.dsmmarketandroid.presentation.util.ProductType
import com.jraska.livedata.test
import io.reactivex.Flowable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`

class PurchaseDetailViewModelTests : BaseTest() {

    @Mock
    private lateinit var getPurchaseDetailUseCase: GetPurchaseDetailUseCase

    @Mock
    private lateinit var interestUseCase: InterestUseCase

    @Mock
    private lateinit var unInterestUseCase: UnInterestUseCase

    @Mock
    private lateinit var getRecommendUseCase: GetRecommendUseCase

    @Mock
    private lateinit var getRelatedUseCase: GetRelatedUseCase

    @Mock
    private lateinit var createRoomUseCase: CreateRoomUseCase

    @Mock
    private lateinit var joinRoomUseCase: JoinRoomUseCase

    private val purchaseDetailModelMapper = PurchaseDetailModelMapper()
    private val recommendModelMapper = RecommendModelMapper()

    private lateinit var viewModel: PurchaseDetailViewModel

    @Before
    fun init() {
        viewModel = PurchaseDetailViewModel(
            getPurchaseDetailUseCase,
            interestUseCase,
            unInterestUseCase,
            getRecommendUseCase,
            getRelatedUseCase,
            createRoomUseCase,
            joinRoomUseCase,
            purchaseDetailModelMapper,
            recommendModelMapper
        )
    }

    @Test
    fun `interest purchase success test`() {
        viewModel.run {
            isInterest.value = false

            `when`(interestUseCase.create(InterestUseCase.Params(0, ProductType.PURCHASE)))
                .thenReturn(Flowable.just(Unit))

            onClickInterest(0)

            assertTrue(isInterest.test().value())
            toastEvent.test().assertValue(R.string.interest)
        }
    }

    @Test
    fun `interest purchase server error test`() {
        viewModel.run {
            isInterest.value = false

            `when`(interestUseCase.create(InterestUseCase.Params(0, ProductType.PURCHASE)))
                .thenReturn(Flowable.error(Exception()))

            onClickInterest(0)

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }

    @Test
    fun `un interest purchase success test`() {
        viewModel.run {
            isInterest.value = true

            `when`(unInterestUseCase.create(UnInterestUseCase.Params(0, ProductType.PURCHASE)))
                .thenReturn(Flowable.just(Unit))

            onClickInterest(0)

            assertFalse(isInterest.test().value())
            toastEvent.test().assertValue(R.string.un_interest)
        }
    }

    @Test
    fun `un interest purchase error test`() {
        viewModel.run {
            isInterest.value = true

            `when`(unInterestUseCase.create(UnInterestUseCase.Params(0, ProductType.PURCHASE)))
                .thenReturn(Flowable.error(Exception()))

            onClickInterest(0)

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }

    @Test
    fun `get purchase detail success test`() {
        val response = PurchaseDetail(listOf(), 0, "", "", "", "", 1, "", true, "", false)
        `when`(getPurchaseDetailUseCase.create(0))
            .thenReturn(Flowable.just(response))

        viewModel.run {
            getPurchaseDetail(0)

            val mapped = purchaseDetailModelMapper.mapFrom(response)
            isInterest.test().assertValue(mapped.isInterest)
            purchaseDetail.test().assertValue(mapped)
        }
    }

    @Test
    fun `get purchase detail failed non exist post test`() {
        `when`(getPurchaseDetailUseCase.create(0))
            .thenReturn(Flowable.error(createHttpException(410)))

        viewModel.run {
            getPurchaseDetail(0)

            toastEvent.test().assertValue(R.string.fail_non_exist_post)
            finishActivityEvent.test().assertHasValue()
        }
    }

    @Test
    fun `get purchase detail failed server error test`() {
        `when`(getPurchaseDetailUseCase.create(0))
            .thenReturn(Flowable.error(Exception()))

        viewModel.run {
            getPurchaseDetail(0)

            toastEvent.test().assertValue(R.string.fail_server_error)
        }
    }

    @Test
    fun getRecommendProductSuccess() {
        val response = listOf(
            Recommend(0, "TITLE", "IMG")
        )
        `when`(getRecommendUseCase.create(Unit))
            .thenReturn(Flowable.just(response))

        viewModel.getRecommendProduct()

        viewModel.recommendList.test().assertValue(recommendModelMapper.mapFrom(response))
    }

    @Test
    fun getRelatedProductSuccess() {
        val response = listOf(
            Recommend(0, "TITLE", "IMG")
        )
        `when`(getRelatedUseCase.create(GetRelatedUseCase.Params(0, ProductType.PURCHASE)))
            .thenReturn(Flowable.just(response))

        viewModel.getRelatedProduct(0)

        viewModel.relatedList.test().assertValue(recommendModelMapper.mapFrom(response))
    }

    @Test
    fun `get related product failed server error`() {
        `when`(getRelatedUseCase.create(GetRelatedUseCase.Params(0, ProductType.PURCHASE)))
            .thenReturn(Flowable.error(Exception()))

        viewModel.getRelatedProduct(0)

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
    }

    @Test
    fun `create room success test`() {
        `when`(createRoomUseCase.create(CreateRoomUseCase.Params(0, ProductType.PURCHASE)))
            .thenReturn(Flowable.just(0))

        `when`(joinRoomUseCase.create(0)).thenReturn(Flowable.just("EMAIL"))

        viewModel.createRoom(0)

        viewModel.intentChatActivityEvent.test().assertHasValue()
    }

    @Test
    fun `create room failed server error test`() {
        `when`(createRoomUseCase.create(CreateRoomUseCase.Params(0, ProductType.PURCHASE)))
            .thenReturn(Flowable.error(Exception()))

        viewModel.createRoom(0)

        viewModel.toastEvent.test().assertValue(R.string.fail_server_error)
    }
}
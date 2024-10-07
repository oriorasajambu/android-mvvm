package id.android.skeleton.features.home.data

import id.android.skeleton.services.session.SessionUseCase
import javax.inject.Inject

class HomeDataRepository @Inject constructor(
    private val session: SessionUseCase,
    private val apiServices: HomeApiServices,
) : HomeRepository {
}
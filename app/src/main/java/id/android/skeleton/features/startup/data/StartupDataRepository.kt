package id.android.skeleton.features.startup.data

import  id.android.skeleton.services.session.SessionUseCase
import javax.inject.Inject

class StartupDataRepository @Inject constructor(
    private val session: SessionUseCase,
    private val apiServices: StartupApiServices,
) : StartupRepository {
}
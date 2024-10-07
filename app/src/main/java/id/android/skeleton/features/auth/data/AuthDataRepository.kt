package id.android.skeleton.features.auth.data

import id.android.skeleton.services.session.SessionUseCase
import javax.inject.Inject

class AuthDataRepository @Inject constructor(
    private val session: SessionUseCase,
    private val apiServices: AuthApiServices,
) : AuthRepository {
}
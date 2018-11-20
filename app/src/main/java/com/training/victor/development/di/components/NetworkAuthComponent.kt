package com.training.victor.development.di.components

import com.training.victor.development.di.modules.NetworkAuthModule
import dagger.Component
import javax.inject.Singleton

/**
 * Created by victorpalmacarrasco on 12/8/18.
 * ${APP_NAME}
 */

@Singleton
@Component(modules = [NetworkAuthModule::class])
interface NetworkAuthComponent
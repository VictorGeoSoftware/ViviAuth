package com.training.victor.development.di.components

import com.training.victor.development.ui.MainActivity
import com.training.victor.development.ui.MedicsActivity
import com.training.victor.development.di.modules.PresenterModule
import com.training.victor.development.di.scopes.ViewScope
import dagger.Subcomponent

/**
 * Created by victorpalmacarrasco on 7/3/18.
 * ${APP_NAME}
 */


@ViewScope
@Subcomponent(modules = [PresenterModule::class])
interface PresenterComponent {
    fun inject(target: MainActivity)
    fun inject(target: MedicsActivity)
}
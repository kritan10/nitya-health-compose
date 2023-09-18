package com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.questions

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.DoctorAddViewModel
import com.kritan.nityahealth.feature_doctor.presentation.doctor_add_screen.DoctorQuestion

@Composable
fun FormData(
    targetQuestionIndex: Int,
    doctorAddViewModel: () -> DoctorAddViewModel
) {
    AnimatedContent(
        targetState = targetQuestionIndex,
        transitionSpec = {
            val animationSpec: TweenSpec<IntOffset> = tween(400)

            val direction = getTransitionDirection(
                initialIndex = initialState,
                targetIndex = targetState
            )

            slideIntoContainer(
                towards = direction,
                animationSpec = animationSpec,
            ) togetherWith slideOutOfContainer(
                towards = direction,
                animationSpec = animationSpec
            )
        },
        label = "surveyScreenDataAnimation"
    ) { targetState ->
        val viewModel = doctorAddViewModel()

        when (viewModel.questionOrder[targetState]) {
            DoctorQuestion.START -> {
                FormStart()
            }

            DoctorQuestion.PERSONAL_INFO -> {
                FormPersonalInfo(
                    personalInfoResponse = viewModel.personalInfoResponse,
                    onPersonalInfoResponse = viewModel::onPersonalInfoResponse
                )
            }

            DoctorQuestion.PROFESSIONAL_INFO -> {
                FormProfessionalInfo(
                    professionalInfo = viewModel.professionalInfoResponse,
                    onResponse = viewModel::onProfessionalInfoResponse
                )
            }


            DoctorQuestion.END -> {
                FormEndPreview()
            }

            DoctorQuestion.DETAILS -> Unit
        }
    }
}


private fun getTransitionDirection(
    initialIndex: Int,
    targetIndex: Int
): AnimatedContentTransitionScope.SlideDirection {
    return if (targetIndex > initialIndex) {
        // Going forwards in the survey: Set the initial offset to start
        // at the size of the content so it slides in from right to left, and
        // slides out from the left of the screen to -fullWidth
        AnimatedContentTransitionScope.SlideDirection.Left
    } else {
        // Going back to the previous question in the set, we do the same
        // transition as above, but with different offsets - the inverse of
        // above, negative fullWidth to enter, and fullWidth to exit.
        AnimatedContentTransitionScope.SlideDirection.Right
    }
}
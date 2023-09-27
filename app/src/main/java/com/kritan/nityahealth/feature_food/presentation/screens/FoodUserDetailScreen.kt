package com.kritan.nityahealth.feature_food.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.kritan.nityahealth.base.extensions.blankWhenNull
import com.kritan.nityahealth.feature_food.presentation.models.FoodUserDetail.Companion.validate
import com.kritan.nityahealth.feature_food.presentation.models.enums.Activeness
import com.kritan.nityahealth.feature_food.presentation.models.enums.DietType
import com.kritan.nityahealth.feature_food.presentation.models.enums.Gender
import com.kritan.nityahealth.feature_food.presentation.models.enums.HeightUnit
import com.kritan.nityahealth.feature_food.presentation.models.enums.WeightUnit
import com.kritan.nityahealth.ui.components.MyButton
import com.kritan.nityahealth.ui.components.MyRadioButton
import com.kritan.nityahealth.ui.components.MySelectionButton
import com.kritan.nityahealth.ui.components.MyTextField
import com.kritan.nityahealth.ui.layouts.MyScaffoldLayout

@Composable
fun FoodUserDetailScreen(
    viewModel: FoodViewModel,
    navigateUp: () -> Unit,
    navigateToFoodHome: () -> Unit
) {
    MyScaffoldLayout(
        title = "Food",
        navigateUp = navigateUp,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        requireScrolling = true
    ) {
        val foodUserDetail = viewModel.uiState.foodUserDetail

        UserHeight(
            height = foodUserDetail?.height,
            onHeightChange = viewModel::onHeightChange,
            heightUnit = foodUserDetail?.heightUnit,
            onHeightUnitChange = viewModel::onHeightUnitChange
        )

        UserWeight(
            weight = foodUserDetail?.weight,
            onWeightChange = viewModel::onWeightChange,
            weightUnit = foodUserDetail?.weightUnit,
            onWeightUnitChange = viewModel::onWeightUnitChange
        )

        UserAge(
            age = foodUserDetail?.age,
            onAgeChange = viewModel::onAgeChange
        )

        UserActiveness(
            userActiveness = foodUserDetail?.activeness,
            onActivenessChange = viewModel::onActivenessChange
        )

        UserGender(
            gender = foodUserDetail?.gender,
            onGenderChange = viewModel::onGenderChange
        )

        UserPreferredDiet(
            dietType = foodUserDetail?.dietType,
            onDietTypeChange = viewModel::onDietTypeChange
        )

        Spacer(modifier = Modifier.weight(1F))

        MyButton(
            label = "Next",
            enabled = viewModel.uiState.foodUserDetail?.validate() ?: false,
            onClick = {
                viewModel.saveCurrentFoodPreferences()
                navigateToFoodHome()
            })

        Spacer(modifier = Modifier.height(4.dp))

    }
}

@Composable
private fun UserHeight(
    height: Int?,
    onHeightChange: (String) -> Unit,
    heightUnit: HeightUnit?,
    onHeightUnitChange: (HeightUnit) -> Unit
) {
    MyFoodTitleBodyLayout(title = "Height") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            MyTextField(
                label = null,
                value = height.blankWhenNull(),
                onValueChange = onHeightChange,
                isSmall = true,
            )
            Spacer(modifier = Modifier.weight(1F))
            MySelectionButton(
                label = "ft/in",
                width = 80,
                isSelected = heightUnit == HeightUnit.FEET_INCH,
                onClick = { onHeightUnitChange(HeightUnit.FEET_INCH) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            MySelectionButton(
                label = "cm",
                width = 80,
                isSelected = heightUnit == HeightUnit.CM,
                onClick = { onHeightUnitChange(HeightUnit.CM) }
            )
        }
    }
}


@Composable
private fun UserWeight(
    weight: Int?,
    onWeightChange: (String) -> Unit,
    weightUnit: WeightUnit?,
    onWeightUnitChange: (WeightUnit) -> Unit
) {
    MyFoodTitleBodyLayout(title = "Weight") {
        Row(verticalAlignment = Alignment.CenterVertically) {
            MyTextField(
                label = null,
                value = weight.blankWhenNull(),
                onValueChange = onWeightChange,
                isSmall = true,
            )
            Spacer(modifier = Modifier.weight(1F))
            MySelectionButton(
                label = "lbs",
                width = 80,
                isSelected = weightUnit == WeightUnit.LBS,
                onClick = { onWeightUnitChange(WeightUnit.LBS) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            MySelectionButton(
                label = "kg",
                width = 80,
                isSelected = weightUnit == WeightUnit.KG,
                onClick = { onWeightUnitChange(WeightUnit.KG) }
            )
        }
    }
}


@Composable
private fun UserAge(
    age: Int?,
    onAgeChange: (String) -> Unit,
) {
    MyFoodTitleBodyLayout(title = "Age") {
        MyTextField(
            label = null,
            value = age.blankWhenNull(),
            onValueChange = onAgeChange,
            isSmall = true,
        )
    }

}


@Composable
private fun UserGender(
    gender: Gender?,
    onGenderChange: (Gender) -> Unit
) {
    MyFoodTitleBodyLayout(title = "Gender") {
        Row {
            MySelectionButton(
                label = "Male",
                isSelected = gender == Gender.MALE,
                width = 120,
                onClick = { onGenderChange(Gender.MALE) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            MySelectionButton(
                label = "Female",
                isSelected = gender == Gender.FEMALE,
                width = 120,
                onClick = { onGenderChange(Gender.FEMALE) }
            )
        }
    }
}


@Composable
private fun UserPreferredDiet(
    dietType: DietType?,
    onDietTypeChange: (DietType) -> Unit
) {
    MyFoodTitleBodyLayout(title = "Your preferred diet") {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            MySelectionButton(
                modifier = Modifier.weight(1F),
                label = "Vegetarian",
                isSelected = dietType == DietType.VEGETARIAN,
                onClick = { onDietTypeChange(DietType.VEGETARIAN) }
            )
            MySelectionButton(
                modifier = Modifier.weight(1F),
                label = "Vegan",
                isSelected = dietType == DietType.VEGAN,
                onClick = { onDietTypeChange(DietType.VEGAN) }
            )
            MySelectionButton(
                modifier = Modifier.weight(1F),
                label = "Non-Veg",
                isSelected = dietType == DietType.NON_VEGETARIAN,
                onClick = { onDietTypeChange(DietType.NON_VEGETARIAN) }
            )

        }
    }
}


@Composable
private fun UserActiveness(
    userActiveness: Activeness?,
    onActivenessChange: (Activeness) -> Unit
) {
    MyFoodTitleBodyLayout(
        title = "How active are you?",
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MyRadioButton(
            label = "Very active",
            selected = userActiveness == Activeness.VERY_ACTIVE,
            onClick = { onActivenessChange(Activeness.VERY_ACTIVE) }
        )
        MyRadioButton(
            label = "Active",
            selected = userActiveness == Activeness.ACTIVE,
            onClick = { onActivenessChange(Activeness.ACTIVE) }
        )
        MyRadioButton(
            label = "Lightly active",
            selected = userActiveness == Activeness.LIGHTLY_ACTIVE,
            onClick = { onActivenessChange(Activeness.LIGHTLY_ACTIVE) }
        )
        MyRadioButton(
            label = "Not very active",
            selected = userActiveness == Activeness.NOT_VERY_ACTIVE,
            onClick = { onActivenessChange(Activeness.NOT_VERY_ACTIVE) }
        )
    }
}

@Composable
private fun MyFoodTitleBodyLayout(
    title: String,
    verticalArrangement: Arrangement.Vertical = Arrangement.spacedBy(8.dp),
    content: @Composable () -> Unit
) {
    Column(verticalArrangement = verticalArrangement) {
        Text(title)
        content()
    }
}


package com.kritan.nityahealth.feature_doctor.presentation.doctor_detail_screen

import androidx.lifecycle.SavedStateHandle
import com.kritan.nityahealth.feature_doctor.data.repository.DoctorRepository
import com.kritan.nityahealth.feature_doctor.data.repository.FakeDoctorRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test

@OptIn(DelicateCoroutinesApi::class, ExperimentalCoroutinesApi::class)
class DoctorDetailViewModelTest {

    private val doctorId = 5

    private lateinit var doctorRepository: DoctorRepository
    private lateinit var savedState: SavedStateHandle

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        savedState = SavedStateHandle(mapOf("id" to doctorId))
        doctorRepository = FakeDoctorRepository(true)
    }

    @Test
    fun test_fetchDoctorWithGivenId() {
        // instantiate view model
        val viewModel = DoctorDetailViewModel(doctorRepository, savedState)

        //check initial doctor value
        assert(viewModel.state.doctor == null)

        //wait for request to complete
        Thread.sleep(3000)

        //check final doctor value
        assert(viewModel.state.doctor != null)
        assert(viewModel.state.doctor?.id == doctorId)
    }

}
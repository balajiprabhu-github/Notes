package com.balajiprabhu.notes.di

import android.app.Application
import androidx.room.Room
import com.balajiprabhu.notes.data.repository.NoteRepositoryImpl
import com.balajiprabhu.notes.data.source.NoteDao
import com.balajiprabhu.notes.data.source.NoteDataBase
import com.balajiprabhu.notes.domain.repository.NoteRepository
import com.balajiprabhu.notes.domain.usecase.AddNoteUseCase
import com.balajiprabhu.notes.domain.usecase.DeleteNoteUseCase
import com.balajiprabhu.notes.domain.usecase.GetNotesUseCase
import com.balajiprabhu.notes.domain.usecase.NoteUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application): NoteDataBase {
        return Room.databaseBuilder(
            application,
            NoteDataBase::class.java,
            NoteDataBase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteRepository(dataBase: NoteDataBase): NoteRepository {
        return NoteRepositoryImpl(dataBase.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repository: NoteRepository): NoteUseCases {
        return NoteUseCases(
            getNotesUseCase = GetNotesUseCase(repository),
            deleteNoteUseCase = DeleteNoteUseCase(repository),
            addNoteUseCase = AddNoteUseCase(repository)
        )
    }
}
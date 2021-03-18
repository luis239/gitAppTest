package com.test.domain


class SearchRepoUseCase (private val gitRepoRepository: GitRepoRepository):
    BaseUseCase<List<ReposResponseModel>, SearchRepoUseCase.Params>() {
    override suspend fun run(params: Params): Either<Failure, List<ReposResponseModel>> {
        return try {
            val response = gitRepoRepository.searchRepo(params.username)
            Either.Right(response)

        } catch (e: Exception) {
            e.printStackTrace()
            Either.Left(SearchFailure(e))
        }
    }

    data class Params(val username: String)

    data class SearchFailure(val error: Exception) : Failure.FeatureFailure(error)
}
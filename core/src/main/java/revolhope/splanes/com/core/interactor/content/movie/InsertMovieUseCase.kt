package revolhope.splanes.com.core.interactor.content.movie

import revolhope.splanes.com.core.data.repository.ContentRepository
import revolhope.splanes.com.core.domain.model.content.Network
import revolhope.splanes.com.core.domain.model.content.movie.Movie
import revolhope.splanes.com.core.domain.model.content.movie.MovieDetails
import revolhope.splanes.com.core.domain.model.user.UserGroupMember

class InsertMovieUseCase(
    private val contentRepository: ContentRepository
) {
    suspend operator fun invoke(
        movie: MovieDetails,
        haveSeen: Boolean = false,
        score: Int = -1,
        network: Network = Network.UNKNOWN,
        recommendedTo: List<UserGroupMember>,
        comments: String = ""
    ): Boolean = contentRepository.insertMovie(
        movie = movie,
        seenByUser = haveSeen,
        userPunctuation = score,
        network = network,
        recommendedTo = recommendedTo,
        userComments = comments
    )
}
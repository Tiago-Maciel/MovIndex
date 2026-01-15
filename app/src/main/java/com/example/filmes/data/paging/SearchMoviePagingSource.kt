package com.example.filmes.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.filmes.data.datasource.remote.MovieRemoteDataSource
import com.example.filmes.data.remote.dto.MovieDto

class SearchMoviePagingSource(
    private val remoteDataSource: MovieRemoteDataSource,
    private val query: String
) : PagingSource<Int, MovieDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieDto> {
        return try {
            if (query.isEmpty()) {
                return LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }

            val page = params.key ?: 1
            val response = remoteDataSource.searchMovies(query, page)

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (page >= response.totalPages) null else page + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, MovieDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}

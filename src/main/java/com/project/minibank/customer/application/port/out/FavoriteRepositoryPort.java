package com.project.minibank.customer.application.port.out;

import com.project.minibank.customer.domain.model.Favorite;

public interface FavoriteRepositoryPort {

    Favorite save(Favorite favorite);
}

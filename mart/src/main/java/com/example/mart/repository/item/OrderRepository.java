package com.example.mart.repository.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import com.example.mart.entity.item.Order;
import com.example.mart.entity.product.QBook;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {

    default Predicate makePredicate(String type, String keyword) {
        BooleanBuilder builder = new BooleanBuilder();
        QBook qBook = QBook.book;

        // id>0 : range scan
        builder.and(qBook.id.gt(0L));

        return builder;
    }
}

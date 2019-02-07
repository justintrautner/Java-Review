package com.justin.review.repositories;

import org.springframework.data.repository.CrudRepository;

import com.justin.review.models.Message;



public interface MessageRepository extends CrudRepository<Message, Long>{

}

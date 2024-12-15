package com.bpb.hssb.ch5.bookclub.repository;

import org.springframework.data.repository.CrudRepository;

import com.bpb.hssb.ch5.bookclub.domain.BookCopy;

public interface BookCopyJPARepository extends CrudRepository<BookCopy, Integer>{

}
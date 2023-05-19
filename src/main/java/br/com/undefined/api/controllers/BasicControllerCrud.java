package br.com.undefined.api.controllers;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BasicControllerCrud<D, I> {

    public ResponseEntity<List<D>> findAll();

    public ResponseEntity<D> findById(I id);

    public ResponseEntity<Void> create(D dtoBody);

    public ResponseEntity<Void> update(D tBody, I id);

    public ResponseEntity<Void> delete(I id);

}

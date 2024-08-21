package com.demo.parseApi.controllers;

import com.demo.parseApi.dto.ParseDTO;
import io.openlineage.sql.OpenLineageSql;
import io.openlineage.sql.SqlMeta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/parse", produces = "application/json")
public class ParseController {

    @PostMapping(value = "/parseSQL")
    public ResponseEntity<String> parse(@RequestBody @Valid ParseDTO parseDTO) {
        List<String> ls = new ArrayList<>();
        ls.add(parseDTO.getSql());
        Optional<SqlMeta> sqlMetaOptional = OpenLineageSql.parse(ls);
        SqlMeta sqlMeta = null;
        if (sqlMetaOptional.isPresent()) {
            sqlMeta = sqlMetaOptional.get();
        }
        parseDTO.setResult(String.valueOf(sqlMeta));
        return new ResponseEntity<>(parseDTO.getResult(), HttpStatus.OK);

    }
}
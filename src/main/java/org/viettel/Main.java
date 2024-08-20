package org.viettel;

import com.google.gson.Gson;
import io.openlineage.sql.SqlMeta;
import io.openlineage.sql.OpenLineageSql;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {
        List<String> ls = new ArrayList<>();
        post("/parseSql", (req, res) -> {
            Map<String, String> map = JsonUtil.parse(req.body());
            ls.add(map.get("sql"));
            Optional<SqlMeta> sqlMetaOptional = OpenLineageSql.parse(ls);            SqlMeta sqlMeta = null;
            if (sqlMetaOptional.isPresent()) {
                sqlMeta = sqlMetaOptional.get();
            }
            return sqlMeta;
        });
    }
}

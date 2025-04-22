package com.app.util;

import com.app.model.Response;

import java.sql.SQLException;

public interface RiskyFunctionAnyType {
    Response<?> execute() throws SQLException;
}

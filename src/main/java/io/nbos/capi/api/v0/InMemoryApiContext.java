package io.nbos.capi.api.v0;

import io.nbos.capi.api.v0.models.TokenApiModel;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vivekkiran on 6/23/16.
 */

public class InMemoryApiContext extends AbstractApiContext {

    HashMap<String, Object> store = new HashMap<>();
    HashMap<String, String> hosts = new HashMap<>();
    HashMap<String, TokenApiModel> tokens = new HashMap<>();

    public InMemoryApiContext() {
    }

    public InMemoryApiContext(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }

    @Override
    public void init() {
    }

    @Override
    public Map getClientCredentials() {
        return (Map) store.get("client.credentials");
    }

    @Override
    public void setClientCredentials(Map map) {
        store.put("client.credentials", map);
    }

    public void setClientToken(TokenApiModel tokenApiModel) {
        store.put("token.client", tokenApiModel);
    }

    public TokenApiModel getClientToken() {
        return (TokenApiModel) store.get("token.client");
    }

    public void setUserToken(String moduleName, TokenApiModel tokenApiModel) {
        tokens.put(moduleName, tokenApiModel);
        if (tokens.get(".") == null) {
            tokens.put(".", tokenApiModel);
        }
    }

    public TokenApiModel getUserToken(String moduleName) {
        TokenApiModel tokenApiModel = tokens.get(moduleName);
        if (tokenApiModel == null) {
            tokenApiModel = tokens.get(".");
        }
        return tokenApiModel;
    }

    @Override
    public void setHost(String moduleName, String host) {
        hosts.put(moduleName, host);
    }

    @Override
    public String getHost(String moduleName) {
        return hosts.get(moduleName);
    }

}

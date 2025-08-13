package org.example.access.match.worker;

import org.example.access.match.Match;

public class WorkerMatch implements Match {

    private String id;
    private String password;
    private boolean valid;

    public WorkerMatch(String id, String password) {
        this.id = id;
        this.password = password;
        this.valid = isValidMatch(id, password);
    }

    @Override
    public boolean idMatch(String id) {
        return this.id != null && this.id.equals(id);

    }

    @Override
    public boolean passwordMatch(String password) {
        return this.password != null && this.password.equals(password);
    }

    @Override
    public boolean isValidMatch(String id, String password) {
        return idMatch(id) && passwordMatch(password);
    }
}

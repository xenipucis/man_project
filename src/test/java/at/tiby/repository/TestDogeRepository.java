package at.tiby.repository;

public class TestDogeRepository implements DogeRepository {
    @Override
    public String getDogeData() {
        return "wow";
    }
}
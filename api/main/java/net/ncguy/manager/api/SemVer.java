package net.ncguy.manager.api;

public class SemVer {

    public int major;
    public int minor;
    public int build;

    @Override
    public String toString() {
        return major + "." + minor + "." + build;
    }
}

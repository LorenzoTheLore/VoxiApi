package it.voxibyte.voxiapi.configuration.data;

import it.voxibyte.voxiapi.annotation.VoxiRoot;

import java.util.List;

public class RootData {
    private List<String> packages;

    public static RootData of(VoxiRoot voxiRoot) {
        return new RootData(
                List.of(voxiRoot.packages())
        );
    }

    private RootData(List<String> packages) {
        this.packages = packages;
    }

    public List<String> getPackages() {
        return packages;
    }

}

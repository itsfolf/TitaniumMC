plugins {
    `java-library`
    toothpick
}

toothpick {
    forkName = "Titanium"
    groupId = "me.titaniumtown"
    val versionTag = System.getenv("BUILD_NUMBER")
        ?: "\"${gitCmd("rev-parse", "--short", "HEAD").output}\""
    forkVersion = "git-$forkName-$versionTag"
    forkUrl = "https://github.com/Titaniumtown/TitaniumMC"

    minecraftVersion = "1.16.4"
    nmsPackage = "1_16_R3"
    nmsRevision = "R0.1-SNAPSHOT"

    upstream = "Purpur"
    upstreamBranch = "origin/ver/1.16.4"

    paperclipName = "titaniumclip.jar"

    server {
        project = project(":$forkNameLowercase-server")
        patchesDir = rootProject.projectDir.resolve("patches/server")
    }
    api {
        project = project(":$forkNameLowercase-api")
        patchesDir = rootProject.projectDir.resolve("patches/api")
    }
}

subprojects {
    repositories {
        mavenCentral()
        maven("https://repo.aikar.co/content/groups/aikar/")
        maven("https://nexus.velocitypowered.com/repository/velocity-artifacts-snapshots/")
        maven("https://libraries.minecraft.net")
        mavenLocal()
    }

    java {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
        withSourcesJar()
    }
}

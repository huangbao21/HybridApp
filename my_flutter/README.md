# my_flutter

A new flutter module project.

### 远程依赖 aar
.android/Flutter/build.gradle

```gradle
// add extra code
apply plugin: 'maven'
ext {
    // 从Github上clone下来的项目的本地地址
    GITHUB_REPO_PATH = "D:\\f\\github\\chips_flutter_library"
    PUBLISH_GROUP_ID = 'bj.chips'
    PUBLISH_ARTIFACT_ID = 'test_flutter_library'
    PUBLISH_VERSION = '1.0.0'
}
uploadArchives {
    repositories {
        mavenDeployer {
            //本地maven仓库地址,也可以使用远程maven仓库
            def deployPath = file(project.GITHUB_REPO_PATH)
            repository(url: "file://${deployPath.absolutePath}")
            pom.project {
                groupId project.PUBLISH_GROUP_ID
                artifactId project.PUBLISH_ARTIFACT_ID
                version project.PUBLISH_VERSION
            }
        }
    }
}
```

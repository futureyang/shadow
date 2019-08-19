# shadow
给View添加阴影效果
#### 添加依赖

在项目的build.gradle中添加：

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
        }
    }
在APP模块下的build.gradle中添加依赖：

    dependencies {
        implementation 'com.github.futureyang:shadow:1.0.0'
    }

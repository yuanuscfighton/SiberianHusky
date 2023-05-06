# SiberianHusky


# 修改build.gradle文件内容

compileSdk rootProject.ext.compileSdkVersion

minSdk rootProject.ext.minSdkVersion
targetSdk rootProject.ext.targetSdkVersion

def configs = rootProject.ext.dependencies
dependencies {
    configs.each {_, value ->
        implementation value
    }
}
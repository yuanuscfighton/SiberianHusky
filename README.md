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

# 运行main函数
修改 .idea / gradle.xml文件
<option name="delegatedBuild" value="false" />
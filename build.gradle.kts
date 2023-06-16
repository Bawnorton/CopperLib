plugins {
    id("java")
}

group = "com.bawnorton"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains:annotations:24.0.0")
    implementation("org.projectlombok:lombok:1.18.26")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0") {
        exclude(group = "com.google.code.gson", module = "gson") // CVE-2022-25647
    }
    implementation("com.squareup.retrofit2:adapter-rxjava3:2.9.0")
    implementation("com.google.code.gson:gson:2.8.9") // Patched version
    implementation("io.reactivex.rxjava3:rxjava:3.1.6")
}

tasks.test {
    useJUnitPlatform()
}
package org.lombold.blueprints.hexagonal;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(packages = "org.lombold.blueprints.hexagonal")
public class HexagonalArchitectureTest {

    /**
     * application must not depend on any adapter (one-way dependency: adapters -> application).
     */
    @ArchTest
    static final ArchRule application_should_not_depend_on_adapters =
            noClasses()
                    .that().resideInAPackage("..application..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..adapter..");

    /**
     * Ports live in the application and must be interfaces.
     */
    @ArchTest
    static final ArchRule ports_must_be_interfaces =
            classes()
                    .that().resideInAnyPackage("..application.port..")
                    .should().beInterfaces();

    /**
     * application must not depend on technical frameworks (Spring, JPA, etc.).
     * Keep the application technology-agnostic.
     */
    @ArchTest
    static final ArchRule application_must_be_framework_free =
            noClasses()
                    .that().resideInAPackage("..application..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage(
                            "org.springframework..",
                            "jakarta.persistence..",
                            "javax.persistence..",
                            "org.hibernate.."
                    );
}

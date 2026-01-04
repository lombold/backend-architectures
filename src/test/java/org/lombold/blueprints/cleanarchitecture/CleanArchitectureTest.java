// java
package org.lombold.blueprints.cleanarchitecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "org.lombold.blueprints.cleanarchitecture",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class CleanArchitectureTest {

    @ArchTest
    static final ArchRule domain_only_depends_on_itself_and_java =
            classes().that().resideInAPackage("..domain..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "java..",
                            "..domain..",
                            "..common..",
                            "lombok.."
                    );

    @ArchTest
    static final ArchRule application_only_depends_on_domain_and_java =
            classes().that().resideInAPackage("..application..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "java..",
                            "..application..",
                            "..domain..",
                            "..common.."
                    );

    @ArchTest
    static final ArchRule adapters_only_depend_on_app_domain_and_java =
            classes().that().resideInAPackage("..adapter..")
                    .should().onlyDependOnClassesThat()
                    .resideInAnyPackage(
                            "java..",
                            "lombok..",
                            "..adapter..",
                            "..application..",
                            "..domain..",
                            "..common.."
                    );

    @ArchTest
    static final ArchRule inbound_adapters_do_not_depend_on_outbound =
            noClasses().that().resideInAPackage("..adapter.controllers..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..adapter.gateways..");

    @ArchTest
    static final ArchRule outbound_adapters_do_not_depend_on_inbound =
            noClasses().that().resideInAPackage("..adapter.gateways..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..adapter.controllers..");

    @ArchTest
    static final ArchRule no_other_layers_may_depend_on_config =
            noClasses().that()
                    .resideInAnyPackage("..domain..", "..application..", "..adapter..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..config..");

    @ArchTest
    static final ArchRule only_frameworks_layer_may_depend_on_frameworks =
            noClasses().that()
                    .resideOutsideOfPackage("..frameworks..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..spring..", "..jakarta..", "..hibernate..");
}
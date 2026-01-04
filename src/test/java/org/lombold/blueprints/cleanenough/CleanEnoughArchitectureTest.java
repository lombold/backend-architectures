// java
package org.lombold.blueprints.cleanenough;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

@AnalyzeClasses(
        packages = "org.lombold.blueprints.cleanenough",
        importOptions = ImportOption.DoNotIncludeTests.class
)
public class CleanEnoughArchitectureTest {

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
}
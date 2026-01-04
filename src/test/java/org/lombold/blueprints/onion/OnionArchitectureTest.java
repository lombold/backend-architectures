package org.lombold.blueprints.onion;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.onionArchitecture;
import static com.tngtech.archunit.library.dependencies.SlicesRuleDefinition.slices;

@AnalyzeClasses(
        packages = "org.lombold.blueprints.onion",
        importOptions = {ImportOption.DoNotIncludeTests.class}
)
public class OnionArchitectureTest {

    // High-level onion definition (library rule)
    @ArchTest
    static final ArchRule onion_architecture_is_respected =
            onionArchitecture()
                    .domainModels("..core.domain.model..")
                    .domainServices("..core.domain.service..")
                    .applicationServices("..core.application..")
                    .adapter("web", "..ui.web..")
                    .adapter("db", "..infrastructure.db..")
                    .adapter("config", "..config..")
                    .ensureAllClassesAreContainedInArchitectureIgnoring(
                            "org.lombold.blueprints.onion"
                    );

    // Domain model must be pure (no dependencies to inner/outer services or adapters)
    @ArchTest
    static final ArchRule domain_model_must_not_depend_on_other_layers =
            noClasses().that()
                    .resideInAPackage("..domain.model..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..domain.service..",
                            "..application..",
                            "..adapter..");

    // Domain services depend only on domain model (and JDK/3rd-party), never on application or adapters
    @ArchTest
    static final ArchRule domain_services_must_not_depend_on_outer_layers =
            noClasses().that()
                    .resideInAPackage("..domain.service..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..application..",
                            "..adapter..");

    // Application services depend on domain, but not on adapters
    @ArchTest
    static final ArchRule application_services_must_not_depend_on_adapters =
            noClasses().that()
                    .resideInAPackage("..application..")
                    .should().dependOnClassesThat()
                    .resideInAnyPackage("..adapter..");

    // Adapters are allowed to depend on application + domain, but not the other way round
    @ArchTest
    static final ArchRule adapters_are_outermost =
            noClasses().that()
                    .resideInAnyPackage("..domain..", "..application..")
                    .should().dependOnClassesThat()
                    .resideInAPackage("..adapter..");

    // No cyclic dependencies between main layers
    @ArchTest
    static final ArchRule no_cycles_between_main_layers =
            slices()
                    .matching("org.lombold.blueprints.onion.(*)..")
                    .should().beFreeOfCycles();
}
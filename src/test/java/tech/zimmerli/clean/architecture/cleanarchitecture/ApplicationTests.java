package tech.zimmerli.clean.architecture.cleanarchitecture;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;
import org.springframework.modulith.docs.Documenter;

import com.tngtech.archunit.base.DescribedPredicate;
import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

class ApplicationTests {
  static JavaClasses classFile = new ClassFileImporter().importPackages("tech.zimmerli.clean.architecture.cleanarchitecture");

    @Test
    void writeDocumentationSnippets() {
  
      var modules = ApplicationModules.of(CleanArchitectureApplication.class).verify();
  
      new Documenter(modules)
        .writeModulesAsPlantUml()
        .writeIndividualModulesAsPlantUml();
    }

    @Test
    void cleanarchitectureTest() {
      
      layeredArchitecture()
      .consideringAllDependencies()
      .layer("Representation").definedBy("..representation..")
      .layer("Domain").definedBy("..domain..")
      .layer("Infrastructure").definedBy("..infrastructure..")
      .layer("UseCases").definedBy("..useCases..")
  
      .whereLayer("Representation").mayNotBeAccessedByAnyLayer()
      .whereLayer("Infrastructure").mayNotBeAccessedByAnyLayer()
      .whereLayer("Representation").mayOnlyAccessLayers("UseCases", "Domain")
      .whereLayer("Infrastructure").mayOnlyAccessLayers("UseCases", "Domain")
      .whereLayer("UseCases").mayOnlyAccessLayers("Domain")
      .whereLayer("Domain").mayNotAccessAnyLayer()
      .ignoreDependency(
                DescribedPredicate.alwaysTrue(),
                JavaClass.Predicates.resideOutsideOfPackages("tech.zimmerli.clean..")
            )
      .check(classFile);
    }
  }
  
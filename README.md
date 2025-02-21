# Jenkins Project Documentation

This documentation provides an overview of the Jenkins project. It includes details about the **Job DSL scripts** and
the **vars shared library functions**.

## Table of Contents

1. [Introduction](#introduction)
2. [Jobs](#jobs)
    - [Example: Pipeline Job DSL](#example-pipeline-job-dsl)
    - [Parameters](#parameters)
3. [Shared Library: `vars`](#shared-library-vars)
    - [Available Functions](#available-functions)
        - [buildProject](#buildproject)
    - [Usage Examples](#usage-examples)
4. [Contributing](#contributing)
5. [License](#license)

---

## Introduction

This Jenkins project leverages the **Pipeline as Code** concept using:

1. **Job DSL**: Manage Jenkins job configurations via scripts.
2. **Shared Libraries (`vars/`)**: Reusable code for Jenkins pipelines.

By using this project structure, you can easily automate pipeline creation and share common functionality.

---

## Jobs

In the `jobs` folder, you define scripts to create Jenkins jobs dynamically using the **Job DSL Plugin**.

### Example: Pipeline Job DSL

Below is an example of a Job DSL script that creates a pipeline job:

```groovy
// Pipeline Job to build the project
pipelineJob('Project-Build') {
    // Description of the job
    description('Pipeline job to build the project from the repository.')

    // Parameters for the job
    parameters {
        // Specify the project name
        stringParam('PROJECT_NAME', 'my-project', 'The name of the project to build')
        // Specify the branch
        stringParam('BRANCH', 'main', 'The branch name to build from')
    }

    // Define the pipeline script
    definition {
        cps {
            script("""
            pipeline {
                agent any

                stages {
                    stage('Checkout') {
                        steps {
                            echo 'Checking out code...'
                        }
                    }
                    stage('Build') {
                        steps {
                            echo 'Building the project...'
                        }
                    }
                }
            }
            """.stripIndent())
            sandbox(true)
        }
    }
}
```

### Parameters

| Parameter Name | Type   | Default Value | Description                       |
|----------------|--------|---------------|-----------------------------------|
| `PROJECT_NAME` | String | `my-project`  | The name of the project to build. |
| `BRANCH`       | String | `main`        | The branch to build from.         |

---

## Shared Library: `vars`

The `vars` folder contains reusable functions to streamline pipeline code.

### Available Functions

#### `buildProject`

The `buildProject` function provides a reusable way to define and build a Jenkins pipeline for a specific project and
branch.

```groovy
/**
 * Build Project lib library function.
 *
 * This function triggers a build for a given project and branch.
 *
 * @param projectName The name of the project to build
 * @param branch The branch on which the code should be built
 */
def call(String projectName, String branch) {
    pipeline {
        agent any

        stages {
            stage('Checkout') {
                steps {
                    echo "Checking out ${projectName} on branch ${branch}"
                    checkout scm
                }
            }
            stage('Build') {
                steps {
                    echo "Building ${projectName} on branch ${branch}..."
                }
            }
        }
    }
}
```

### Usage Examples

#### `buildProject`

Here’s how you use the `buildProject` function in your pipeline:

```groovy
@Library('my-lib-library') _

buildProject('example-project', 'development')
```

---

## Contributing

To contribute:

1. Fork the repository.
2. Create a feature branch (`feature/your-feature`).
3. Commit your changes.
4. Open a Pull Request.

### Code Style

- Use GroovyDoc for documenting methods and classes.
- Use consistent indentation (4 spaces).
- Test code thoroughly before submitting.

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.

---
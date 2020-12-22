.PHONY: help
help:  ## Show this help.
	@egrep '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-22s\033[0m %s\n", $$1, $$2}'

.PHONY: test
test:  ## Run tests.
	sbt clean coverage test coverageReport

.PHONY: lint
lint:  ## Run linting.
	sbt scalafmtCheckAll

.PHONY: format-code
format-code:  ## Format the code using Scalafmt
	sbt scalafmtAll

#!/usr/bin/env sh

# Copyright 2015 the original author or authors.
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#      https://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

APP_NAME="Gradle"
APP_BASE_NAME=gradle
APP_HOME=$(cd "$(dirname "$0")" && pwd -P) || exit

APP_JAR="$APP_HOME/.gradle/wrapper/gradle-wrapper.jar"
if [ ! -f "$APP_JAR" ]; then
  mkdir -p "$APP_HOME/.gradle/wrapper"
fi

exec java -jar "$APP_JAR" "$@"
#!/bin/bash

# set the target directory
TARGET_DIR=~/development/tools

# check if the directory exists, create it if it doesn't
if [ ! -d $TARGET_DIR ]; then
  mkdir -p $TARGET_DIR
fi

# set the artifact details
ARTIFACT_GROUP="org.openapitools"
ARTIFACT_NAME="openapi-generator-cli"
ARTIFACT_LATEST_VERSION=$(curl -s "https://repo1.maven.org/maven2/${ARTIFACT_GROUP//.//}/${ARTIFACT_NAME}/maven-metadata.xml" | grep -oP '<latest>\K.*(?=</latest>)')
ARTIFACT_FILENAME="${ARTIFACT_NAME}-${ARTIFACT_LATEST_VERSION}.jar"

# check if the artifact exists in the target directory, download it if it doesn't
if [ ! -f "$TARGET_DIR/$ARTIFACT_FILENAME" ]; then
  echo "Downloading $ARTIFACT_FILENAME"
  wget "https://repo1.maven.org/maven2/${ARTIFACT_GROUP//.//}/${ARTIFACT_NAME}/${ARTIFACT_LATEST_VERSION}/${ARTIFACT_FILENAME}" -P "$TARGET_DIR"
else
  echo "$ARTIFACT_FILENAME already exists in $TARGET_DIR"
fi

# -------------------------------------------------

# Set the path to the JSON file
JSON_FILE="options.json"

# Check if the JSON file exists
if [ ! -f "$JSON_FILE" ]; then
  echo "Error: JSON file not found: $JSON_FILE"
  exit 1
fi

# Read JSON file and flatten properties
OPTIONS=""
while IFS="=" read -r key value; do
  # Skip empty lines
  [ -z "$key" ] && continue

  # Append each property to the OPTIONS variable
  OPTIONS+="--additional-properties=$key=$value "
done < <(jq -r "to_entries|map(\"\(.key)=\(.value|tostring)\")|.[]" "$JSON_FILE")

# Run the Java command with flattened properties
java -jar "$TARGET_DIR/$ARTIFACT_FILENAME" generate \
  -g java \
  -i api/openapi.yaml \
  -o ./ \
  $OPTIONS \
  --skip-validate-spec \
  --global-property skipFormModel=false

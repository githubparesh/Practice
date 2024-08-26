#!/bin/bash

CSV_FILE="/Users/pareshviradiya/Documents/gitlab/Practice/src/main/java/Data/test_data.csv"
API_URL="https://jsonplaceholder.typicode.com/posts/"  # Replace with actual API URL

# Function to fetch data from API
fetch_data() {
    local id=$1
    curl -s "${API_URL}/${id}"
}

# Read CSV file and validate data
while IFS=, read -r id title body userId; do
    if [[ $id != "id" ]]; then  # Skip header
        echo "Validating ID: $id"
        response=$(fetch_data $id)

        # Extract values from API response
        actualId=$(echo $response | jq -r '.id')
        actualTitle=$(echo $response | jq -r '.title')
        actualBody=$(echo $response | jq -r '.body' | tr '\n' ' ' | xargs)  # Replace newlines with spaces
        actualUserID=$(echo $response | jq -r '.userId')

        # Check against expected values
        if [[ $id == $actualId && $title == $actualTitle && $body == $actualBody && $userId == $actualUserID ]]; then
            echo "Validation passed for ID: $id"
        else
            echo "Validation failed for ID: $id"
            echo "Body: $body , $actualBody"
            echo "Expected: $id , $title, $body, $userId"
            echo "Actual: $actualId, $actualTitle, $actualBody, $actualUserID"
        fi
    fi
done < "$CSV_FILE"
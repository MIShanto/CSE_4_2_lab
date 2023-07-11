def rle_decode(data):
    decoded_data = ''
    count = ''
    digitOccured = False
    for char in data:
        if char == chr(123):
            # Skip the escape character
            continue
        elif char.isdigit() and digitOccured == False:
            # Start a new count
            count += char
            digitOccured = True
        else:
            # Add the character repeated by the count
            decoded_data += char * int(count)
            count = ''
            digitOccured = False
    return decoded_data

# Decode the data
with open('RLE_out.txt', 'r') as f:
        data = f.read()

decoded_data = rle_decode(data)

# Write the decoded data to a file
with open('RLE_decoded.txt', 'w') as f:
    f.write(decoded_data)

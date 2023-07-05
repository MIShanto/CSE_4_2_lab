def rle_encode(data):
    count = 1
    prev = data[0]
    encoded_data = ''
    for i in range(1, len(data)):
        if data[i].isdigit():
            # Escape any digits with 
            encoded_data += str(count) + prev + chr(123)
            count = 1
            prev = data[i]
        elif data[i] != prev:
            # Add the count and character to the encoding
            encoded_data += str(count) + prev
            count = 1
            prev = data[i]
        else:
            # Increment the counter
            if count != 9:
                count += 1
            else:
                encoded_data += str(count) + prev
                count = 1
                prev = data[i]
    # Add the last count and character
    encoded_data += str(count) + prev
    return encoded_data

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

# Open file for reading
with open('RLE_in.txt', 'r') as f:
    data = f.read()

# Encode the data
encoded_data = rle_encode(data)

# Write the encoded data to a file
with open('RLE_out.txt', 'w') as f:
    f.write(encoded_data)

# Decode the data
with open('RLE_out.txt', 'r') as f:
        data = f.read()

decoded_data = rle_decode(data)

# Write the decoded data to a file
with open('RLE_decoded.txt', 'w') as f:
    f.write(decoded_data)
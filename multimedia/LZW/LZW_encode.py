def compress_file(uncompressed):
    dictionary_size = 256
    dictionary = {chr(i): i for i in range(dictionary_size)}

    result = []
    buffer = ""
    for char in uncompressed:
        new_string = buffer + char
        if new_string in dictionary:
            buffer = new_string
        else:
            result.append(dictionary[buffer])
            dictionary[new_string] = dictionary_size
            dictionary_size += 1
            buffer = char

    if buffer:
        result.append(dictionary[buffer])

    return result

# Open file for reading
with open('LZW_in.txt', 'r') as f:
    data = f.read()

# Encode the data
encoded_data = compress_file(data)

# Write the encoded data to a file
with open('LZW_encoded.txt', "w") as output_file:
    output_file.write(str(encoded_data))

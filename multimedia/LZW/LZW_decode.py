def decompress_file(compressed):
    dictionary_size = 256
    dictionary = {i: chr(i) for i in range(dictionary_size)}

    buffer = chr(compressed[0])
    result = [buffer]
    for code in compressed[1:]:
        if code in dictionary:
            entry = dictionary[code]
        elif code == dictionary_size:
            entry = buffer + buffer[0]
        else:
            raise ValueError("Invalid compressed code")

        result.append(entry)

        dictionary[dictionary_size] = buffer + entry[0]
        dictionary_size += 1

        buffer = entry
    return result

# Decode the data
with open('LZW_encoded.txt', 'r') as f:
        data = f.read()
compressed_data = eval(data)  # Convert the string representation to a list

decoded_data = decompress_file((compressed_data))
print(decoded_data)
# Write the decoded data to a file
with open("LZW_out.txt", "w") as output_file:
        output_file.write("".join(decoded_data))

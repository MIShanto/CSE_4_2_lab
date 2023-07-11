import json

def decode(encoded, codes):
    reverse_codes = {code: char for char, code in codes.items()}
    decoded = ""
    current_code = ""
    for bit in encoded:
        current_code += bit
        if current_code in reverse_codes:
            decoded += reverse_codes[current_code]
            current_code = ""
    return decoded

# Read encoded string from text file
with open('encoded.txt', 'r') as f:
    encoded = f.read()

# Read codes from text file as a JSON object
with open('codes.json', 'r') as f:
    codes = json.load(f)

decoded = decode(encoded, codes)

# Save decoded string to text file
with open('decoded.txt', 'w') as f:
    f.write(decoded)

# Print results
print(f"Decoded string: {decoded}")

index_register = [0,0,0,0]

def inp(statement):
    return input(statement)

def binary2octal(input_binary, length):
    #input_binary type is a string, the return val is an octal number, data type is string
    decimal_val = int(input_binary, 2)
    oct_val = oct(decimal_val)[2:]
    if (length-len(oct_val)) >= 0:
        offsetZERO = (length-len(oct_val))
    else:
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + oct_val

def decimal2binary(x, length):
    # x is a int value
    val = bin(x)[2:]
    if (length-len(val)) >= 0:
        offsetZERO = (length-len(val))
    else:
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + val

def decimal2octal(x, length):
    # x is a int value
    val = oct(x)[2:]
    if (length-len(val)) >= 0:
        offsetZERO = (length-len(val))
    else:
        print("Invalid input: the value is bigger than maximum.")
        return
    return '0' * offsetZERO + val

def process_binstr_register(opcode, content_list):
    bin_opcode = decimal2binary(opcode, 6)
    decimal_IX = int(content_list[1])
    decimal_address = int(content_list[2])
    bin_R = decimal2binary(int(content_list[0]), 2)
    bin_IX = decimal2binary(decimal_IX, 2)
    if len(content_list) == 3:
        bin_I = str(0)
        if decimal_IX == 0:
            bin_address = decimal2binary(decimal_address, 5)
        elif decimal_IX  <= 3:
            bin_address = decimal2binary(decimal_address + index_register[decimal_IX], 5)
        else:
            print("Invalid input: the index register is in valid.")
            return
    elif int(content_list[3]) == 1:
        bin_I = str(1)
        if decimal_IX == 0:
            bin_address = decimal2binary(decimal_address, 5)
        elif decimal_IX  <= 3:
            bin_address = decimal2binary(decimal_address + index_register[decimal_IX], 5)
        else:
            print("Invalid input: the index register is in valid.")
            return
    bin_str = bin_opcode + bin_R + bin_IX + bin_I + bin_address
    return bin_str

def process_binstr_val(opcode, content_list):
    bin_opcode = decimal2binary(opcode, 6)
    decimal_IX = int(content_list[0])
    decimal_address = int(content_list[1])
    bin_R = decimal2binary(0, 2)
    bin_IX = decimal2binary(decimal_IX, 2)
    if len(content_list) == 2:
        bin_I = str(0)
        if decimal_IX == 0:
            bin_address = decimal2binary(decimal_address, 5)
        elif decimal_IX  <= 3:
            bin_address = decimal2binary(decimal_address + index_register[decimal_IX], 5)
        else:
            print("Invalid input: the index register is in valid.")
            return
    bin_str = bin_opcode + bin_R + bin_IX + bin_I + bin_address
    return bin_str

def process_str(input_str):
    opcode = 0
    if input_str == 'HLT':
        print('HLT')
        return 'HLT'
    if (len(input_str.split()) != 2):
        print("Invalid input string.")
        return
    op = input_str.split()[0]
    content = input_str.split()[1]
    con_value = content.split(',')
    print("content: " , content)
    print("con_value: ", con_value, type(con_value))
    if op == "LOC":
        opcode = 0
        return "LOC", int(con_value[0])
    elif op == "Data":
        opcode = 0
        if con_value[0] == 'End':
            con_value[0] = 1024
        return "Data", decimal2octal(int(con_value[0]), 6)
    elif op == "LDR":
        opcode = 1
        bin_str = process_binstr_register(opcode, con_value)
        return "LDR", binary2octal(bin_str, 6)
    elif op == "STR":
        opcode = 2
        bin_str = process_binstr_register(opcode, con_value)
        return "STR", binary2octal(bin_str, 6)
    elif op == "LDA":
        opcode = 3
        bin_str = process_binstr_register(opcode, con_value)
        return "LDA", binary2octal(bin_str, 6)
    elif op == "LDX":
        opcode = 4
        bin_str = process_binstr_val(opcode, con_value)
        return "LDX", binary2octal(bin_str, 6)
    elif op == "STX":
        opcode = 5
        bin_str = process_binstr_val(opcode, con_value)
        return "STX", binary2octal(bin_str, 6)
    # the code below is not available now, only draft.
    elif op == "JZ":
        opcode = 6
        bin_str = process_binstr_val(opcode, con_value)
        return "JZ", binary2octal(bin_str, 6)
    elif op == "JNE":
        opcode = 7
        bin_str = process_binstr_val(opcode, con_value)
        return "JNE", binary2octal(bin_str, 6)
    elif op == "JCC":
        opcode = 8
        bin_str = process_binstr_register(opcode, con_value)
        return "JCC", binary2octal(bin_str, 6)
    elif op == "JMA":
        opcode = 9
        bin_str = process_binstr_val(opcode, con_value)
        return "JMA", binary2octal(bin_str, 6)
    elif op == "JSR":
        opcode = 10
        bin_str = process_binstr_val(opcode, con_value)
        return "JSR", binary2octal(bin_str, 6)
    elif op == "RFS":
        opcode = 11
        bin_str = process_binstr_val(opcode, con_value)
        return "RFS", binary2octal(bin_str, 6)
    elif op == "SOB":
        opcode = 12
        bin_str = process_binstr_register(opcode, con_value)
        return "SOB", binary2octal(bin_str, 6)
    elif op == "JGB":
        opcode = 13
        bin_str = process_binstr_register(opcode, con_value)
        return "JGB", binary2octal(bin_str, 6)
    elif op == "AMR":
        opcode = 14
        bin_str = process_binstr_register(opcode, con_value)
        return "AMR", binary2octal(bin_str, 6)
    elif op == "SMR":
        opcode = 15
        bin_str = process_binstr_register(opcode, con_value)
        return "SMR", binary2octal(bin_str, 6)
    elif op == "AIR":
        opcode = 16
        bin_str = process_binstr_register(opcode, con_value)
        return "AIR", binary2octal(bin_str, 6)
    elif op == "SIR":
        opcode = 17
        bin_str = process_binstr_register(opcode, con_value)
        return "SIR", binary2octal(bin_str, 6)
    elif op == "MLT":
        opcode = 18
        bin_str = process_binstr_register(opcode, con_value)
        return "MLT", binary2octal(bin_str, 6)
    elif op == "DVD":
        opcode = 19
        bin_str = process_binstr_register(opcode, con_value)
        return "DVD", binary2octal(bin_str, 6)
    elif op == "TRR":
        opcode = 20
        bin_str = process_binstr_register(opcode, con_value)
        return "TRR", binary2octal(bin_str, 6)
    elif op == "AND":
        opcode = 21
        bin_str = process_binstr_register(opcode, con_value)
        return "AND", binary2octal(bin_str, 6)
    elif op == "ORR":
        opcode = 22
        bin_str = process_binstr_register(opcode, con_value)
        return "ORR", binary2octal(bin_str, 6)
    elif op == "NOT":
        opcode = 23
        bin_str = process_binstr_register(opcode, con_value)
        return "NOT", binary2octal(bin_str, 6)
    elif op == "SRC":
        opcode = 24
        bin_str = process_binstr_register(opcode, con_value)
        return "SRC", binary2octal(bin_str, 6)
    elif op == "RRC":
        opcode = 25
        bin_str = process_binstr_register(opcode, con_value)
        return "RRC", binary2octal(bin_str, 6)
    elif op == "IN":
        opcode = 26
        bin_str = process_binstr_register(opcode, con_value)
        return "ORR", binary2octal(bin_str, 6)
    elif op == "OUT":
        opcode = 27
        bin_str = process_binstr_register(opcode, con_value)
        return "ORR", binary2octal(bin_str, 6)
    elif op == "CHK":
        opcode = 28
        bin_str = process_binstr_register(opcode, con_value)
        return "ORR", binary2octal(bin_str, 6)
    elif op == "FADD":
        opcode = 29
        bin_str = process_binstr_register(opcode, con_value)
        return "FADD", binary2octal(bin_str, 6)
    elif op == "FSUB":
        opcode = 30
        bin_str = process_binstr_register(opcode, con_value)
        return "FSUB", binary2octal(bin_str, 6)
    elif op == "VADD":
        opcode = 31
        bin_str = process_binstr_register(opcode, con_value)
        return "VADD", binary2octal(bin_str, 6)
    elif op == "VSUB":
        opcode = 32
        bin_str = process_binstr_register(opcode, con_value)
        return "VSUB", binary2octal(bin_str, 6)
    elif op == "CNVRT":
        opcode = 33
        bin_str = process_binstr_register(opcode, con_value)
        return "CNVRT", binary2octal(bin_str, 6)
    elif op == "LDFR":
        opcode = 34
        bin_str = process_binstr_register(opcode, con_value)
        return "LDFR", binary2octal(bin_str, 6)
    elif op == "STFR":
        opcode = 35
        bin_str = process_binstr_register(opcode, con_value)
        return "STFR", binary2octal(bin_str, 6)
    elif op == "SETCCE":
        opcode = 36
        bin_str = process_binstr_val(opcode, con_value)
        return "SETCCE", binary2octal(bin_str, 6)
    
    else:
        return "Invalid input string."

running_flag = True
# LOC_pointer is a deci
LOC_pointer = 0

while running_flag:
    in_str = inp("Input: ")
    instruction = process_str(in_str)
    if instruction[0] == 'LOC':
        LOC_format = decimal2octal(LOC_pointer, 6)
        instruction_format = decimal2octal(0, 6)
        LOC_pointer = int(instruction[1])
        print(LOC_format, ' ', instruction_format)
    elif instruction == 'HLT':
        running_flag = False
        LOC_format = decimal2octal(LOC_pointer, 6)
        instruction_format = decimal2octal(0, 6)
        print(LOC_format, ' ', instruction_format)
    else:
        LOC_format = decimal2octal(LOC_pointer, 6)
        print(LOC_format, ' ', instruction[1])
        LOC_pointer = LOC_pointer + 1

    

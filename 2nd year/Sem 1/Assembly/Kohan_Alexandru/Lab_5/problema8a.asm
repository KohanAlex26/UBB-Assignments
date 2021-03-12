bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,scanf,printf,gets
import scanf msvcrt.dll
import printf msvcrt.dll
import gets msvcrt.dll
; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
    readformat db '%d', 0
    printformat db '%x', 0
    citireMesaj db 'Cititi un numar decimal: ',0
    mesaj db 'a = <%d> (base 10), a = <%x> (base 16)',0
    nr dd 0
; our code starts here
segment code use32 class=code
    start:
        ; ...
    ;8. A negative number a (a: dword) is given. Display the value of that number in base 10 and in the base 16 in the following format: "a = <base_10> (base 10), a = <base_16> (base 16)" 

        ;mesaj
    push dword citireMesaj
    call [printf]
    add esp,4*1
   
        ;citire in zecimal 

    push dword nr 
    push dword readformat 
    call [scanf] 
    add esp, 4*2 
    
    
        ;afisare in hexazecimal 
    push dword [nr]
    push dword [nr]
    push dword mesaj
    call [printf] 
    add esp, 4*3
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program

bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf,scanf,gets              ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import printf msvcrt.dll                         ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions
import scanf msvcrt.dll
import gets msvcrt.dll
; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
x db 0
readformat db '%x',0
citire db 'cititi un numar hexa: ',0
afisarenumar db 'Ati citit numarul %x',0
k dd 10
result dq 0
afisare db 'The number is %d The result of %x * k is %d ',0
newline db 10,0
; our code starts here
segment code use32 class=code
    start:
        ; ...
    push dword citire
    call [printf]
    add esp,4*1
    
    push dword x
    push dword readformat
    call [scanf]
    add esp,4*2
    
    push dword[x]
    push dword afisarenumar
    call [printf]
    add esp,4*2
    
    push newline
    call [printf]
    add esp,4*1
    
    mov eax,[x]
    cdq
    mov ebx,[k]
    imul ebx
    mov [result+0],eax
    mov [result+4],edx
    

    push dword [result]
    push dword [x]
    push dword [x]
    push dword afisare
    call [printf]
    add esp,4*4
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program

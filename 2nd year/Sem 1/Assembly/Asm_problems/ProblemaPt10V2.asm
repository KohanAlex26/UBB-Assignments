bits 32 ; assembling for the 32 bits architecture

; declare the EntryPoint (a label defining the very first instruction of the program)
global start        

; declare external functions needed by our program
extern exit,printf,scanf,gets               ; tell nasm that exit exists even if we won't be defining it
import exit msvcrt.dll    ; exit is a function that ends the calling process. It is defined in msvcrt.dll
import gets msvcrt.dll
import scanf msvcrt.dll
import printf msvcrt.dll                          ; msvcrt.dll contains exit, printf and all the other important C-runtime specific functions

; our data is declared here (the variables needed by our program)
segment data use32 class=data
    ; ...
s dd 10,4,3,12
len_s equ ($-s)/4
d times len_s db 0
k dd 2
citire db 'Cititi o cifra ',0
formatcifra db '%x',0
afisarecifra db 'Cifra dvs este %x',0
nr dd 0
sum dd 0
finals db 'Suma = %d si numere = %d'
newline db 10,0
; our code starts here
segment code use32 class=code
    start:
        ; ...
    
    push dword citire
    call [printf]
    add esp,4
    
    
    push dword k
    push formatcifra
    call [scanf]
    add esp,4*2
    
    push dword [k]
    push afisarecifra
    call [printf]
    add esp,4*2
    
    push newline
    call[printf]
    add esp,4
    
    mov eax,0
    mov ebx,0
    mov edx,0
    mov ecx,len_s
    mov esi,len_s*4-4
    mov edi,0
    
    
    repeta:
    mov al,[s+esi]
    cbw
    mov bl,[k]
    idiv bl
    ;al-cat ah-rest
    cmp ah,0
    JE adauga
    JNE nuadauga
        adauga:
        mov al,[s+esi]
        cmp al,5
        JG da
        JLE nu
            da:
            mov [d+edi],al
            inc edx
            inc edi
            jmp final
            nu:
            jmp final
        nuadauga:
            jmp final
    final:
    sub esi,4
    loop repeta
    
    
    mov [nr],edx
    mov ecx,[nr]
    mov esi,0
    mov edx,0
    
    repeta2:
    add dl,[d+esi]
    inc esi
    loop repeta2
    
    mov [sum],dl
    
    push dword [nr]
    push dword [sum]
    push dword finals
    call [printf]
    add esp,4*3
        ; exit(0)
        push    dword 0      ; push the parameter for exit onto the stack
        call    [exit]       ; call exit to terminate the program

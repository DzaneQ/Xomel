# Xomel

Aplikacja mobilna do odczytywania plików XML.

## Właściwości techniczne

- Java 17
- Kompilacyjny pakiet SDK API 36.1
- Docelowy pakiet SDK API 36
- Minimalny pakiet SDK API 23
- Gradle 8.13
- Android Gradle Plugin 8.13.2

## Instrukcja

Sklonuj repo:
```
https://github.com/DzaneQ/Xomel
```
```
cd Xomel
```

Upewnij się, że masz ustawione `JAVA_HOME` do aktualnego JDK wersji 17:
```PowerShell
"$env:JAVA_HOME\bin\java.exe" -version
```

Jeśli brak, sprawdź czy masz zainstalowane JDK. Dla Windowsa, to powinno być `C:\Users\(nazwa_użytkownika)\.jdks\(nazwa_JDK)`. Wpisz jako zmienną `JAVA_HOME`:
```PowerShell
$env:JAVA_HOME = "$env:USERPROFILE\.jdks\(nazwa_JDK)"
```

Potem uruchom w celu debugowania:
```
gradlew.bat assembleDebug
```

W mobilce z Androidem włącz opcję `Debugowanie USB` i podłącz go ze sprzętem, na którym jest kod.

W celu zainstalowania wpisz:
```
grandlew.bat installDebug
```

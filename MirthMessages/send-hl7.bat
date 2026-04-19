try {
    $VT = [char]0x0B
    $FS = [char]0x1C
    $CR = [char]0x0D
    $hl7 = "MSH|^~\&|HOSPITAL|FACILITY|MIRTH|CONNECT|20240418120000||ADT^A01|MSG001|P|2.3" + $CR
    $hl7 += "PID|1||PAT001^^^HOSPITAL^MR||Nguyen^Viet^T||19880914|M|||123 Main St^^KL^^50000^MY||+60123456789" + $CR
    $message = $VT + $hl7 + $FS + $CR
    $client = New-Object System.Net.Sockets.TcpClient("localhost", 6661)
    $stream = $client.GetStream()
    $bytes = [System.Text.Encoding]::UTF8.GetBytes($message)
    $stream.Write($bytes, 0, $bytes.Length)
    $stream.Flush()
    Start-Sleep -Seconds 2
    $client.Close()
    Write-Host "Sent successfully!"
} catch {
    Write-Host "ERROR: $_"
}
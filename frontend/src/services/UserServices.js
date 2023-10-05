export function ValidEmail(value) {
    if (!value) {
        setOverlapEmail(false);
        return;
    }

    if (!emailPattern.test(e.target.value)) {
        setOverlapEmail(1);
        return;
    }

    if (timeoutIdRef.current) clearTimeout(timeoutIdRef.current);

    timeoutIdRef.current = setTimeout(async () => {
        const result = await checkEmail(e.target.value);

        if (result.status === 200) {
            setOverlapEmail(2);
        } else {
            setOverlapEmail(0);
        }
    }, 400);
}

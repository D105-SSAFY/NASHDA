import * as Styled from "./style";

export default function MainPage() {
    return (
        <>
            <Styled.Header>
                <Styled.HeadingOne>MainPage</Styled.HeadingOne>
            </Styled.Header>
            <Styled.Main>
                <Styled.Section>
                    <Styled.SectionHeader>
                        <Styled.SectionHeadingTwo>
                            Links
                        </Styled.SectionHeadingTwo>
                    </Styled.SectionHeader>
                    <Styled.SectionList>
                        <Styled.SectionListItem>
                            <Styled.SectionListLink to={"/signin"}>
                                signin
                            </Styled.SectionListLink>
                        </Styled.SectionListItem>
                        <Styled.SectionListItem>
                            <Styled.SectionListLink to={"/signup"}>
                                signup
                            </Styled.SectionListLink>
                        </Styled.SectionListItem>
                    </Styled.SectionList>
                </Styled.Section>
            </Styled.Main>
        </>
    );
}
